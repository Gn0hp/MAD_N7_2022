from django.shortcuts import render
from rest_framework import status, viewsets
from .models import *
from .serializer import *
from drf_yasg import openapi
from drf_yasg.utils import swagger_auto_schema
from rest_framework.decorators import action
from rest_framework.response import Response
from django.forms.models import model_to_dict
from django.core.paginator import Paginator
import json
from django.core import serializers


def queryset_to_json(data):
    json_data = json.loads(serializers.serialize("json", [data]))
    json_tmp = json_data[0]["fields"]
    json_tmp["id"]=json_data[0]["pk"]
    return json_tmp

def queryset_arr_to_json(data):
    json_tmp=[]
    for data_index in data:
        json_tmp.append(queryset_to_json(data_index))
    return json_tmp

def queryset_product_arr_to_json(data):
    json_arr=[]
    for data_index in data:
        json_tmp=queryset_to_json(data_index)
        attribute = json_tmp["attribute"]
        json_tmp["attribute"]=queryset_arr_to_json(Attribute.objects.filter(pk__in=attribute))
        json_tmp["category"]=queryset_to_json(data_index.category)
        json_arr.append(json_tmp)
    return json_arr

# def queryset_category_arr_to_json(data):
#     json_arr=[]
#     for data_index in data:
#         json_tmp=queryset_to_json(data_index)
#         json_arr.append(json_tmp)
#     return json_arr
# Create your views here.
class AttributeViewSet(viewsets.ModelViewSet):
    """
    A viewset that provides the standard actions
    """
    queryset = Attribute.objects.all()
    serializer_class = AttributeSerializer

    @action(detail=False, methods=['post'])
    def create_many(self, request, format=None):
        attributes = request.data
        arr=[]
        for attribute in attributes:
            data=attribute
            serializer = self.get_serializer(data=data)
            serializer.is_valid(raise_exception=True)
            self.perform_create(serializer)
            arr.append(serializer.data["id"])
        return Response({
            'results': arr,
            'count': 0,
        }, status=status.HTTP_200_OK)

class AuthUserViewSet(viewsets.ModelViewSet):
    """
    A viewset that provides the standard actions
    """
    queryset = AuthUser.objects.all()
    serializer_class = AuthUserSerializer

class CategoryViewSet(viewsets.ModelViewSet):
    """
    A viewset that provides the standard actions
    """
    queryset = Category.objects.all()
    serializer_class = CategorySerializer

    id = openapi.Parameter(
        'id', openapi.IN_QUERY, description='id',  type=openapi.TYPE_STRING)
    page = openapi.Parameter(
        'current',
        openapi.IN_QUERY,
        description='Trang',
        type=openapi.TYPE_STRING,
        required=True
    )
    page_size = openapi.Parameter(
        'page_size',
        openapi.IN_QUERY,
        description='Số lượng mỗi trang',
        type=openapi.TYPE_STRING,
        required=True
    )
    @swagger_auto_schema(method="get", manual_parameters=[id, page, page_size])
    @action(detail=False, methods=['get'])
    def get_by(self, request, format=None):
        data = Category.objects.all()
        if request.query_params.get('id', None) != None:
            data = data.filter(
                pk=request.query_params.get('id', None)
            )
        page_size = request.query_params['page_size']
        page = request.query_params['current']

        paginator = Paginator(data, page_size)
        serializer = self.get_serializer(
            paginator.get_page(page), many=True)
        json_data_format=[]
        for data_index in data:
            json_tmp=queryset_to_json(data_index)
            attribute = json_tmp["attribute"]
            data_attribute = Attribute.objects.filter(pk__in=attribute)
            json_tmp["attribute"]=queryset_arr_to_json(data_attribute)
            json_tmp["product"]=queryset_product_arr_to_json(data_index.product_set.all())
            json_data_format.append(json_tmp)
        return Response({
            'results': json_data_format,
            'count': len(json_data_format),
        }, status=status.HTTP_200_OK)

class ProductViewSet(viewsets.ModelViewSet):
    """
    A viewset that provides the standard actions
    """
    queryset = Product.objects.all()
    serializer_class = ProductSerializer

    id = openapi.Parameter(
        'id', openapi.IN_QUERY, description='id',  type=openapi.TYPE_STRING)
    name = openapi.Parameter(
        'name', openapi.IN_QUERY, description='name',  type=openapi.TYPE_STRING)
    category = openapi.Parameter(
        'category', openapi.IN_QUERY, description='category',  type=openapi.TYPE_STRING)
    page = openapi.Parameter(
        'current',
        openapi.IN_QUERY,
        description='Trang',
        type=openapi.TYPE_STRING,
        required=True
    )
    page_size = openapi.Parameter(
        'page_size',
        openapi.IN_QUERY,
        description='Số lượng mỗi trang',
        type=openapi.TYPE_STRING,
        required=True
    )
    @swagger_auto_schema(method="get", manual_parameters=[id, name, category, page, page_size])
    @action(detail=False, methods=['get'])
    def get_by(self, request, format=None):
        data = Product.objects.all()
        if request.query_params.get('id', None) != None:
            data = data.filter(
                pk=request.query_params.get('id', None)
            )
        if request.query_params.get('category', None) != None:
            data = data.filter(
                category=request.query_params.get('category', None)
            )
        arr_data=[]
        if request.query_params.get('name', None) != None:
            for data_index in data:
                if request.query_params.get('name', None) in data_index.name:
                    arr_data.append(data_index)


        if len(arr_data):
            data=arr_data
        page_size = request.query_params['page_size']
        page = request.query_params['current']

        paginator = Paginator(data, page_size)
        serializer = self.get_serializer(
            paginator.get_page(page), many=True)
        json_data_format=queryset_product_arr_to_json(data)
        return Response({
            'results': json_data_format,
            'count': len(json_data_format),
        }, status=status.HTTP_200_OK)
    
    def update(self, request, *args, **kwargs):
        partial = kwargs.pop('partial', False)
        instance = self.get_object()
        data_full = queryset_to_json(instance)
        for key in request.data.keys():
            data_full[key]=request.data[key]
        serializer = self.get_serializer(instance, data=data_full, partial=partial)
        serializer.is_valid(raise_exception=True)
        self.perform_update(serializer)
        print(serializer)

        if getattr(instance, '_prefetched_objects_cache', None):
            # If 'prefetch_related' has been applied to a queryset, we need to
            # forcibly invalidate the prefetch cache on the instance.
            instance._prefetched_objects_cache = {}

        return Response(serializer.data)


class OrderViewSet(viewsets.ModelViewSet):
    """
    A viewset that provides the standard actions
    """
    queryset = Order.objects.all()
    serializer_class = OrderSerializer

    id = openapi.Parameter(
        'id', openapi.IN_QUERY, description='id',  type=openapi.TYPE_STRING)
    user = openapi.Parameter(
        'user', openapi.IN_QUERY, description='user',  type=openapi.TYPE_STRING)
    page = openapi.Parameter(
        'current',
        openapi.IN_QUERY,
        description='Trang',
        type=openapi.TYPE_STRING,
        required=True
    )
    page_size = openapi.Parameter(
        'page_size',
        openapi.IN_QUERY,
        description='Số lượng mỗi trang',
        type=openapi.TYPE_STRING,
        required=True
    )
    @swagger_auto_schema(method="get", manual_parameters=[id, user, page, page_size])
    @action(detail=False, methods=['get'])
    def get_by(self, request, format=None):
        data = Order.objects.all()
        if request.query_params.get('id', None) != None:
            data = data.filter(
                pk=request.query_params.get('id', None)
            )
        if request.query_params.get('user', None) != None:
            data = data.filter(
                user=request.query_params.get('user', None)
            )
        page_size = request.query_params['page_size']
        page = request.query_params['current']

        paginator = Paginator(data, page_size)
        serializer = self.get_serializer(
            paginator.get_page(page), many=True)
        json_data_format=[]
        for data_index in data:
            json_tmp=queryset_to_json(data_index)
            # json_tmp["product"]=queryset_product_arr_to_json(data_index.product)
            product = json_tmp["product"]
            data_product = Product.objects.filter(pk__in=product)
            json_tmp["product"] = queryset_product_arr_to_json(data_product)
            json_data_format.append(json_tmp)
        return Response({
            'results': json_data_format,
            'count': len(json_data_format),
        }, status=status.HTTP_200_OK)




class PaymentViewSet(viewsets.ModelViewSet):
    """
    A viewset that provides the standard actions
    """
    queryset = Payment.objects.all()
    serializer_class = PaymentSerializer

class CartViewSet(viewsets.ModelViewSet):
    """
    A viewset that provides the standard actions
    """
    queryset = Cart.objects.all()
    serializer_class = CartSerializer

    id = openapi.Parameter(
        'id', openapi.IN_QUERY, description='id',  type=openapi.TYPE_STRING)
    user = openapi.Parameter(
        'user', openapi.IN_QUERY, description='user',  type=openapi.TYPE_STRING)
    page = openapi.Parameter(
        'current',
        openapi.IN_QUERY,
        description='Trang',
        type=openapi.TYPE_STRING,
        required=True
    )
    page_size = openapi.Parameter(
        'page_size',
        openapi.IN_QUERY,
        description='Số lượng mỗi trang',
        type=openapi.TYPE_STRING,
        required=True
    )
    @swagger_auto_schema(method="get", manual_parameters=[user, page, page_size])
    @action(detail=False, methods=['get'])
    def get_by(self, request, format=None):
        data = Cart.objects.all()
        if request.query_params.get('user', None) != None:
            data = data.filter(
                user=request.query_params.get('user', None)
            )
        page_size = request.query_params['page_size']
        page = request.query_params['current']

        paginator = Paginator(data, page_size)
        serializer = self.get_serializer(
            paginator.get_page(page), many=True)
        if len(data)!=1:
            return Response( data={"Cho cai user chuan vao cai de"}, status=status.HTTP_400_BAD_REQUEST)
        data_cart=data[0].cartproduct_set.all()
        json_data_format=[]
        for data_index in data_cart:
            json_tmp={}
            json_tmp["product"]=queryset_to_json(data_index.product)
            json_tmp["quantity"]=data_index.quantity
            json_data_format.append(json_tmp)
        return Response({
            'results': json_data_format,
            'count': len(json_data_format),
        }, status=status.HTTP_200_OK)


    


class CartProductViewSet(viewsets.ModelViewSet):
    """
    A viewset that provides the standard actions
    """
    queryset = CartProduct.objects.all()
    serializer_class = CartProductSerializer

    product = openapi.Parameter(
        'product', openapi.IN_QUERY, description='product',  type=openapi.TYPE_STRING)
    cart = openapi.Parameter(
        'cart', openapi.IN_QUERY, description='cart',  type=openapi.TYPE_STRING)
    quantity = openapi.Parameter(
        'quantity', openapi.IN_QUERY, description='quantity',  type=openapi.TYPE_STRING)
    @swagger_auto_schema(method="get", manual_parameters=[quantity,cart,product])
    @action(detail=False, methods=['get'])
    def create_by(self, request, *args, **kwargs):
        data = {
            "quantity": request.query_params.get('quantity', None),
            "cart": request.query_params.get('cart', None),
            "product": request.query_params.get('product', None)
            }
        serializer = self.get_serializer(data=data)
        serializer.is_valid(raise_exception=True)
        self.perform_create(serializer)
        headers = self.get_success_headers(serializer.data)
        return Response(serializer.data, status=status.HTTP_201_CREATED, headers=headers)