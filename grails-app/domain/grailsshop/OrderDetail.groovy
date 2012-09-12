package grailsshop

class OrderDetail {
    
    static belongsTo = [ book : Book, order : Order ]

    int quantity

    static constraints = {
        quantity(minSize : 1)
    }
    
    String toString() {
        "${book.name}×${quantity}"
    }
}