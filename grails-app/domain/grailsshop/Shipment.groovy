package grailsshop

/**
 * 出荷のドメインクラス
 */
class Shipment {

    static belongsTo = [order :  Order, warehouse: Warehouse]
    Date date
}