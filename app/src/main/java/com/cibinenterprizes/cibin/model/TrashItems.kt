package com.cibinenterprizes.cibin.model

import android.view.View
import android.widget.ImageView


class TrashItems {

    var icons: Int = 0
    var name: String? = null
    var name1: String? = null

    constructor(icons: Int, name: String?, name1: String?) {
        this.icons = icons
        this.name = name
        this.name1 = name1
    }

}