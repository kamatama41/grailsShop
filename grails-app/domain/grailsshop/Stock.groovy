package grailsshop

/**
 * 在庫のドメインクラス
 */
class Stock {

    static belongsTo = [ book : Book]
    int quantity
}
