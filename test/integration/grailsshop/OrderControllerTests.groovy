package grailsshop

import grails.test.WebFlowTestCase

class OrderControllerTests extends WebFlowTestCase {

    def flow
    OrderController controller

    @Override
    protected void setUp() {
        super.setUp()
        controller = new OrderController()
    }

    def getFlow () {
         flow
    }

    def setFlow(flow) {
        this.flow = flow
    }

    void testIndex() {
        controller.index()
        assert controller.response.redirectedUrl == "/order/search"
    }

    void testGoSearch() {
        controller.goSearch()
        assert controller.response.redirectedUrl == "/order/search"
    }

    void testGoCart() {
        controller.goCart()
        assert controller.response.redirectedUrl == "/order/cart"
    }

    // メニューから検索
    void testOrderFlowMenuToSearch() {
        setFlow(controller.searchFlow)
        startFlow()
        assertCurrentStateEquals("search")
    }

    // 検索画面で「検索」
    void testOrderFlowSearchToSearch() {
        setFlow(controller.searchFlow)
        controller.params.title = ""
        controller.params.author = ""

        startFlow()
        assertCurrentStateEquals "search"
        signalEvent "doSearch"
        assertCurrentStateEquals "search"
    }

    // 検索画面で「詳細」
    void testOrderFlowSearchToDetail() {
        setFlow(controller.searchFlow)
        controller.params.id = 1

        startFlow()
        assertCurrentStateEquals "search"
        signalEvent("detail")
        assertCurrentStateEquals "displayDetail"
    }

    // 検索画面で「カートに入れる」
    void testOrderFlowSearchToToCart() {
        setFlow(controller.searchFlow)
        controller.params.id = 1
        controller.session.order = new Order()

        startFlow()
        assertCurrentStateEquals "search"
        signalEvent"toCart"
        assertCurrentStateEquals"search"
    }

    // 詳細画面で「検索に戻る」
    void testOrderFlowDisplayDetailToSearch() {
        setFlow(controller.searchFlow)
        controller.params.id = 1

        startFlow()
        assertCurrentStateEquals("search")
        signalEvent("detail")
        assertCurrentStateEquals("displayDetail")
        signalEvent("search")
        assertCurrentStateEquals("search")
    }

    // 詳細画面で「カートに入れる」
    void testOrderFlowDitailToCart() {
        setFlow(controller.searchFlow)
        controller.params.id = 1
        controller.session.order = new Order()

        startFlow()
        assertCurrentStateEquals("search")
        signalEvent("detail")
        assertCurrentStateEquals("displayDetail")
        signalEvent("toCart")
        assertCurrentStateEquals("displayDetail")
    }

    // メニューから「カートの中を見る」
    void testOrderFlowDisplayDetailToCart() {
        setFlow(controller.cartFlow)
        controller.session.order = new Order()

        startFlow()
        assertCurrentStateEquals("showCart")
    }

    // カートの中を見る画面で「カートの中を編集」
    void testOrderFlowShowCartToEditCart() {
        setFlow(controller.cartFlow)
        controller.session.order = new Order()

        startFlow()
        assertCurrentStateEquals("showCart")
        signalEvent("editCart")
        assertCurrentStateEquals("editCart")
    }

    // カートの中を見る画面で「清算」
    void testOrderFlowShowCartToCredeitCartConfirm () {
        setFlow(controller.cartFlow)
        controller.session.order = new Order()

        startFlow()
        assertCurrentStateEquals("showCart")
        signalEvent("goCredit")
        assertCurrentStateEquals("showCart")
    }

    // カートの中を編集画面で「編集」
    void testOrderFlowEditCartToSubmit() {
        setFlow(controller.cartFlow)
        controller.session.order = new Order()

        startFlow()
        assertCurrentStateEquals("showCart")
        signalEvent("editCart")
        assertCurrentStateEquals("editCart")
        signalEvent("submit")
        assertCurrentStateEquals("showCart")
    }

    // カートの中を編集画面で「キャンセル」
    void testOrderFlowEditCartToCancel() {
        setFlow(controller.cartFlow)
        controller.session.order = new Order()

        startFlow()
        assertCurrentStateEquals("showCart")
        signalEvent("editCart")
        assertCurrentStateEquals("editCart")
        signalEvent("cancel")
        assertCurrentStateEquals("showCart")
    }

    // カートの内容確認画面で「次へ」
    void testOrderFlowCreditToNext() {
        setFlow(controller.creditFlow)
        controller.session.order = new Order()

        startFlow()
        assertCurrentStateEquals("creditCartConfirm")
        signalEvent("next")
        assertCurrentStateEquals("creditCustomerConfirm")
    }

    // 支払情報確認画面で「次へ」
    void testOrderFlowCreditAccountConfirmToNext () {
        setFlow(controller.creditFlow)
        controller.session.order = new Order()
        controller.params.name = "my name"
        controller.params.address = "my address"
        controller.params.phoneNumber = "my phoneNumber"
        controller.params.email = "my@email.co.jp"
        controller.params.paymentMethod = "my paymentMethod"

        startFlow()
        assertCurrentStateEquals("creditCartConfirm")
        signalEvent("next")
        assertCurrentStateEquals("creditCustomerConfirm")
        signalEvent("next")
        assertCurrentStateEquals("creditFinalConfirm")
    }

    // 支払情報確認画面絵で「戻る」
    void testOrderFlowCreditAccountConfirmToBack() {
        setFlow(controller.creditFlow)
        controller.session.order = new Order()
        controller.params.name = "my name"
        controller.params.address = "my address"
        controller.params.phoneNumber = "my phoneNumber"
        controller.params.email = "my@email.co.jp"
        controller.params.paymentMethod = "my paymentMethod"

        startFlow()
        assertCurrentStateEquals("creditCartConfirm")
        signalEvent("next")
        assertCurrentStateEquals("creditCustomerConfirm")
        signalEvent("back")
        assertCurrentStateEquals("creditCartConfirm")
    }

    // 最終確認画面で「完了」
    void testOrderFlowCreditFinalConfirmToNext() {
        setFlow(controller.creditFlow)
        controller.session.order = new Order()
        controller.params.name = "my name"
        controller.params.address = "my address"
        controller.params.phoneNumber = "my phoneNumber"
        controller.params.email = "my@email.co.jp"
        controller.params.paymentMethod = "my paymentMethod"

        startFlow()
        assertCurrentStateEquals("creditCartConfirm")
        signalEvent("next")
        assertCurrentStateEquals("creditCustomerConfirm")
        signalEvent("next")
        assertCurrentStateEquals("creditFinalConfirm")
        signalEvent("next")
        assertCurrentStateEquals("creditComplete")
    }

    // 最終確認画面で「戻る」
    void testOrderFlowCreditFinalConfirmToBack() {
        setFlow(controller.creditFlow)
        controller.session.order = new Order()
        controller.params.name = "my name"
        controller.params.address = "my address"
        controller.params.phoneNumber = "my phoneNumber"
        controller.params.email = "my@email.co.jp"
        controller.params.paymentMethod = "my paymentMethod"

        startFlow()
        assertCurrentStateEquals("creditCartConfirm")
        signalEvent("next")
        assertCurrentStateEquals("creditCustomerConfirm")
        signalEvent("next")
        assertCurrentStateEquals("creditFinalConfirm")
        signalEvent("back")
        assertCurrentStateEquals("creditCustomerConfirm")
    }
}