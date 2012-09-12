package grailsshop

/**
 * 倉庫のドメインクラス
 */
class Warehouse {

    static hasMany = [ stocks : Stock]
    String address
    String phoneNumber
}
