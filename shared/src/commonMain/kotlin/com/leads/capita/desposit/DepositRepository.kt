package com.leads.capita.desposit

interface DepositRepository {
    fun getDeposit(): String
    fun getDepositStatus(): String
}