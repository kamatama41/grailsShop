package grailsshop

class OrderController {

    def index() {
        redirect(action: "search")
    }

    def goSearh() {
        redirect(action: "search")
    }

    def goCart() {
        redirect(action: "cart")
    }

    def searchFlow = {
        // 検索
        search {
            on("doSearch").to "search"
            on("detail").to "desplayDetail"
            on("toCart").to "search"
        }

        // 詳細
        displayDetail {
            on("search").to "search"
            on("toCart").to "desplayDetail"
        }
    }

    def cartFlow = {
        start {
            action {
            }
            on("success").to "showCart"
        }

        // カートの中をみる
        showCart {
            on("editCart").to "editCart"
            on("goCredit").to "showCart"
        }

        // カートの中身を編集する
        editCart {
            on("submit").to "showCart"
            on("cancel").to "showCart   "
        }
    }

    def creditFlow = {
        start {
            action {
            }
            on("success").to "creditCartConfirm"
        }

        // カート内容確認
        creditCartConfirm {
            on("next").to "creditCustomerConfirm"
        }

        // 顧客情報入力
        creditCustomerConfirm {
            on("next").to "creditFinalConfirm"
            on("back").to "creditCartConfirm"
        }

        // 最終確認
        creditFinalConfirm {
            on("next").to "creditComplete"
            on("back").to "creditCustomerConfirm"
        }

        creditComplete {
        }
    }
}
