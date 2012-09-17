package grailsshop

import grails.test.WebFlowTestCase

class OrderControllerTests extends WebFlowTestCase {

    def flow

    def getFlow () {
         flow
    }

    def setFlow(flow) {
        this.flow = flow
    }

    void testIndex() {
        def orderController = new OrderController()
        orderController.index()
        assert orderController.response.redirectedUrl == "/order/search"
    }

    void testSearchFlowStart() {
        setFlow(new OrderController().searchFlow)
        startFlow()
        assertCurrentStateEquals("search")
    }

}
