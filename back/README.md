

<!-- Chạy không cần docker -->
<!-- import thu vien va setup moi truong -->
sudo apt-get update
sudo apt install python3.8 -y
sudo apt install python3-pip
sudo apt install python-dev libxml2-dev libxslt1-dev antiword unrtf poppler-utils pstotext tesseract-ocr flac ffmpeg lame libmad0 libsox-fmt-mp3 sox libjpeg-dev swig -y

sudo pip3 install django
sudo pip3 install djangorestframework
sudo pip3 install drf-yasg -U
sudo pip3 install psycopg2-binary
sudo pip3 install textract
sudo pip3 install python-telegram-bot
sudo pip3 install elasticsearch
sudo pip3 install distro

sudo DEBIAN_FRONTEND=noninteractive apt install tshark -y

sudo pip3 install python-docx
sudo pip3 install pdfminer3
sudo pip3 install openpyxl
sudo pip3 install scapy
sudo pip3 install django-cors-headers


<!-- run server -->
python3 manage.py makemigrations
python3 manage.py migrate
sudo python3 manage.py runserver 9900


<!-- chạy cần docker -->
cd DLP_API
sudo apt update
sudo apt upgrade
sudo apt install docker-compose -y
curl
sudo apt install curl
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
sudo apt update
sudo apt upgrade

docker-compose up -d
<!-- server chay tren port 9900 -->

