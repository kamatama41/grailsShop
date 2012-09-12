package grailsshop

class Order {

    Customer customer
    static hasMany = [ orderDetails : OrderDetail ]
    // 注文日
    Date date

    static constraints = {
        customer()
        date()
    }

    // データベースの登録名のマッピング
    static mapping = {
        table '`order`'
    }
}
