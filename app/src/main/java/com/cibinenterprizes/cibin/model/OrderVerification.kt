package com.cibinenterprizes.cibin.model


class OrderVerification{

    var date: String? = null
    var time: String? = null
    var orderKey: String? = null
    var verification: String? = null
    var snipper: ContactDetailsOfCustomer? = null
    var position:  PositionModel? = null

    constructor(){

    }

    constructor(date: String?, time: String?, orderKey: String?, verification: String?, snipper: ContactDetailsOfCustomer, position: PositionModel) {
        this.date = date
        this.time = time
        this.orderKey = orderKey
        this.verification = verification
        this.snipper = snipper
        this.position = position
    }


}