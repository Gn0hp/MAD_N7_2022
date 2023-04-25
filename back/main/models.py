from django.db import models

# Create your models here.


class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.BooleanField()
    username = models.CharField(unique=True, max_length=150)
    first_name = models.CharField(max_length=150)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.BooleanField()
    is_active = models.BooleanField()
    date_joined = models.DateTimeField()
    
    class Meta:
        managed = False
        db_table = 'auth_user'
    
    def create(self, data):
        print(data)

class Attribute(models.Model):
    type_choie = (
        ('number', 'number'),
        ('string', 'string')
    )
    name = models.CharField(max_length=255)
    value = models.CharField(max_length=255)
    type = models.CharField(
        choices=type_choie, max_length=50, default='string')
    

class Category(models.Model):
    name = models.CharField(max_length=255)
    description = models.TextField()
    attribute = models.ManyToManyField(Attribute,related_name='attribute',blank=True)
    class Meta:
        ordering = ('name',)

# class CategoryAttribute(models.Model):
#     category = models.ForeignKey(Category, on_delete=models.CASCADE)
#     attribute = models.ForeignKey(Attribute, on_delete=models.CASCADE)
#     class Meta:
#         unique_together = (('category', 'attribute'))


class Product(models.Model):
    name = models.CharField(max_length=255)
    description = models.TextField()
    count = models.IntegerField()
    attribute = models.ManyToManyField(Attribute,blank=True)
    category = models.ForeignKey(Category,blank=True,on_delete=models.CASCADE)
    price = models.IntegerField(null=True)
    views = models.IntegerField(blank=True)

class Cart(models.Model):
    user = models.ForeignKey(AuthUser,on_delete=models.CASCADE)

class CartProduct(models.Model):
    cart = models.ForeignKey(Cart,on_delete=models.CASCADE)
    product = models.ForeignKey(Product,on_delete=models.CASCADE)
    quantity = models.IntegerField(blank=True)

class Order(models.Model):
    user = models.ForeignKey(AuthUser,on_delete=models.CASCADE)
    product = models.ManyToManyField(Product)   
    total_price = models.IntegerField()
    status = models.CharField(max_length=255)
    from_addr = models.CharField(max_length=255)
    to_addr = models.CharField(max_length=255)

class Payment(models.Model):
    type_choie = (
        ('cash', 'cash'),
        ('momo', 'momo'),
        ('momo', 'momo'),
        ('momo', 'momo'),
        ('momo', 'momo'),
        ('momo', 'momo'),
    )
    type = models.CharField(
        choices=type_choie, max_length=50, default='cash')
    status = models.CharField(max_length=255)
    user = models.ForeignKey(AuthUser,on_delete=models.CASCADE)
    order = models.ForeignKey(Order,on_delete=models.CASCADE,blank=True)

    

    
