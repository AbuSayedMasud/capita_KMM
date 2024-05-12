package com.leads.capita.payment

interface PaymentRepository {
    fun getPayment():String
    fun getPaymentStatus():String

}