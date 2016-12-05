> For windows use `Git Bash`

####Credentials
- admin@gmail.com admin
- admin1@gmail.com admin
- user@yandex.ru password
- user@fun.com password

User, Admin
-----------
- profile
    * register: 
    `curl -X POST -H "Content-Type:application/json" http://localhost:8080/register -d '{"name":"New User","email":"user@office.com","password":"password"}'` 
    * check profile: 
    `curl http://localhost:8080/profile --user user@office.com:password`
    * delete profile: 
    `curl -X DELETE http://localhost:8080/profile --user user@office.com:password`
- restaurants
    * to check all restaurants: 
        * `curl http://localhost:8080/restaurants`
        * `curl http://localhost:8080/restaurants?order=desc`
    * to check all restaurants by location (distance - optional): 
        * ` curl "http://localhost:8080/restaurants?longitude=-32.7612&latitude=-34.9241"`
        * ` curl "http://localhost:8080/restaurants?longitude=-32.7612&latitude=-34.9241&distance=600"`
    * get particular restaurant: 
    `curl http://localhost:8080/restaurants/the_table`
- bookings
    * to book a place: 
    `curl -X POST -H "Content-Type:application/json" http://localhost:8080/bookings/the_table --user user@office.com:password -d '{"date":"2016-12-09","time":"15:00"}'`
    * to check all your bookings: 
    `curl http://localhost:8080/bookings --user user@office.com:password`
    * to delete booking for particular date: 
    `curl -X DELETE -H "Cotent-Type:application/json" http://localhost:8080/bookings?date=2016-12-09 --user user@office.com:password`

admin
-----
- resturants
    * check all owned restaurants: http://localhost:8080/admin/restaurants
    `curl http://localhost:8080/admin/restaurants --user admin@gmail.com:admin`
    * save restaurant: 
    `curl -X POST -H "Content-Type:application/json" http://localhost:8080/admin/restaurants --user admin@gmail.com:admin -d '{"name":"Fine Eat","location":{"x":"10.0","y":"-40.0"},"menu":[{"description":"Porridge","preparingTime":"30","price":"10.0"},{"description":"Soup","preparingTime":"45","price":"15.0"},{"description":"Grecha","preparingTime":"15","price":"20.0"}],"openTime":"06:00","closeTime":"22:00"}'`
    * delete restaurant: 
    `curl -X DELETE -H "Cotent-Type:application/json" http://localhost:8080/admin/restaurants/the_table --user admin@gmail.com:admin`
- meals
    * save meal: 
    `curl -X POST -H "Content-Type:application/json" http://localhost:8080/admin/restaurants/the_table/meals --user admin@gmail.com:admin -d '{"description":"Salad","preparingTime":"10","price":"10.0"}'`
    * delete meal: 
    `curl -X DELETE -H "Content-Type:application/json" http://localhost:8080/admin/restaurants/the_table/meals?description=Sushi --user admin@gmail.com:admin`
    * delete all meal's for particular restaurant: 
    `curl -X DELETE -H "Content-Type:application/json" http://localhost:8080/admin/restaurants/the_table/meals --user admin@gmail.com:admin`
- bookings
    * check all bookings for particular restaurant
        * with specified date: 
        `curl http://localhost:8080/admin/restaurants/the_table/bookings?date=2016-12-08 --user admin@gmail.com:admin`
        * for today: 
        `curl http://localhost:8080/admin/restaurants/the_table/bookings --user admin@gmail.com:admin`