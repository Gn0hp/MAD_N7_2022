FROM ubuntu:20.04

ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1

ARG DJANGO_ALLOWED_HOSTS
ARG DJANGO_SECRET_KEY
ARG DJANGO_CORS_ORIGIN_WHITELIST

ENV DJANGO_ALLOWED_HOSTS $DJANGO_ALLOWED_HOSTS
ENV DJANGO_SECRET_KEY $DJANGO_SECRET_KEY
ENV DJANGO_CORS_ORIGIN_WHITELIST $DJANGO_CORS_ORIGIN_WHITELIST

ENV TZ=Asia/Ho_Chi_Minh
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apt-get update \
    && apt-get install -y gcc python3-dev musl-dev  libffi-dev netcat

RUN apt-get update
RUN apt install python3.8 -y
RUN apt install python3-pip -y
RUN apt install python-dev libxml2-dev libxslt1-dev antiword unrtf poppler-utils pstotext tesseract-ocr flac ffmpeg lame libmad0 libsox-fmt-mp3 sox libjpeg-dev swig -y

RUN pip3 install django
RUN pip3 install djangorestframework
RUN pip3 install drf-yasg -U
RUN pip3 install psycopg2-binary
RUN pip3 install textract
RUN pip3 install python-telegram-bot
RUN pip3 install elasticsearch
RUN pip3 install distro
RUN pip3 install PyJWT
RUN ls

RUN DEBIAN_FRONTEND=noninteractive apt install tshark -y

RUN pip3 install python-docx
RUN pip3 install pdfminer3
RUN pip3 install openpyxl
RUN pip3 install scapy
RUN pip3 install django-cors-headers

EXPOSE 9900
RUN mkdir /back
WORKDIR /back


COPY . /back/

RUN pip3 install python-dotenv
RUN pip3 install drf-spectacular
RUN pip3 install django-filter
RUN pip3 install djoser
RUN pip3 install -r requirements.txt
RUN pip3 install scipy==1.7.1 numpy==1.18.5 scikit-learn==0.24.2 --no-cache-dir
RUN pip3 install elasticsearch
RUN pip3 install -U protobuf==3.8.0
RUN pip3 install Pillow



