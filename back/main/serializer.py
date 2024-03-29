from rest_framework import serializers
from .models import *
class AttributeSerializer(serializers.ModelSerializer):

    class Meta:
        model = Attribute
        fields = "__all__"

class CategorySerializer(serializers.ModelSerializer):

    class Meta:
        model = Category
        fields = "__all__"

class CartSerializer(serializers.ModelSerializer):
    class Meta:
        model = Cart
        fields = "__all__"

class ProductSerializer(serializers.ModelSerializer):

    class Meta: 
        model = Product
        fields = "__all__"
        
class OrderSerializer(serializers.ModelSerializer):

    class Meta: 
        model = Order
        fields = "__all__"
        
class PaymentSerializer(serializers.ModelSerializer):

    class Meta: 
        model = Payment
        fields = "__all__"
        
class CartProductSerializer(serializers.ModelSerializer):

    class Meta: 
        model = CartProduct
        fields = "__all__"

class AuthUserSerializer(serializers.ModelSerializer):
    class Meta: 
        model = AuthUser
        fields = "__all__"
        