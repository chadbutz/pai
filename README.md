Commands:

mvn -U -X package docker:build -Dpush.image=false
docker-compose -f docker-compose.yml up
