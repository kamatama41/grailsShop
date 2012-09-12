package grailsshop

class Account {

    Customer customer
    
    static hasMany = [orders : Order]
    String name
    String password
    static constraints = {
        customer(nullable: true)
        name(unique: true)
        password(blank: false)
    }
}
