package com.cibinenterprizes.cibin.model

class ContactDetailsOfCustomer {
    var area: String? = null
    var phone: String? = null
    var number_of_kg: String? = null

    constructor(){

    }

    constructor(area: String?, phone: String?, number_of_kg: String?) {
        this.area = area
        this.phone = phone
        this.number_of_kg = number_of_kg
    }
}