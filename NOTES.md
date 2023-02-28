# MONGO CRUD

alway use batch insert instead of for loop single insert

`.insertMany()` with `List<Documents`

```
set MONGO_URL='mongodb+srv://nus-iss.lxwsyup.mongodb.net'
set USERNAME='akimori'

mongoimport --uri %MONGO_URL% --username=%USERNAME% -d bgg -c game --jsonArray --file ./game.json --drop
```

mongo syntax

```
db.friends.insert({
    name: "fred",
    age: 18
})
db.friends.find({name: "fred"})

db.friends.updateOne({
    name: {$regex: "red", $options: "i"}
},{
    $set: {age: 21}  
})

db.friends.updateOne({
    name: {$regex: "red", $options: "i"}
},{
    $inc: {age: 1}  
})

db.friends.updateOne({
    name: {$regex: "red", $options: "i"}
},{
    $push: {hobbies: "photography"}  
})

db.friends.updateOne({
    name: {$regex: "red", $options: "i"}
},{
    $pop: {hobbies: -1}  
})
```

# MYSQL

## LOGIN

`mysql -uroot -p`

## import sql

`source <filepath>`   to run the .sql file

`show databases;`

`DESCRIBE table;` to see columns

## user privileges

`grant all privileges on bgg.* to 'fred'@'%';`

`flush privileges` to confirm/set privileges
