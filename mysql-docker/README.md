How to run mysql for the dealerlocater
```
docker build -t custom-mysql .
```

```
docker run -d --name custom-mysql -p 3307:3306 custom-mysql 
```
port exposed is 3307 and root user password is root

you can check the running status by 

```
docker exec -it custom-mysql mysql -uroot -proot -e "USE cdk; SELECT * FROM dealers;"
```

if you got the table data, our dealer locater api is good to go.