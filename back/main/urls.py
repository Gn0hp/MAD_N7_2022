from django.urls import path, include
from rest_framework.routers import DefaultRouter
from . import views
# Create a router and register our viewsets with it.
router = DefaultRouter()
router.register(r'attribute', views.AttributeViewSet)
router.register(r'category', views.CategoryViewSet)
router.register(r'product', views.ProductViewSet)
router.register(r'order', views.OrderViewSet)
router.register(r'payment', views.PaymentViewSet)
router.register(r'cart', views.CartViewSet)
router.register(r'cart_product', views.CartProductViewSet)
router.register(r'user', views.AuthUserViewSet)
router.register(r'ai', views.AiView)
# router.register(r'category', views.CategoryViewSet)

# The API URLs are now determined automatically by the router.
urlpatterns = [
    path('', include(router.urls)),
]