if [ "$DATABASE" = "postgres" ]
then
    echo "Waiting for postgres..."

    while ! nc -z $SQL_HOST $SQL_PORT; do
      sleep 0.1
    done

    echo "PostgreSQL started"
fi

echo "Starting Migrate db .."

python3 manage.py makemigrations

python3 manage.py migrate

python3 manage.py runserver 0.0.0.0:9900

