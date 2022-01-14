package com.cibinenterprizes.cibin.model

class PositionModel {

    var latitude: Double? = null
    var longitude: Double? = null

    constructor(){

    }

    constructor(latitude: Double?, longitude: Double?) {
        this.latitude = latitude
        this.longitude = longitude
    }
}