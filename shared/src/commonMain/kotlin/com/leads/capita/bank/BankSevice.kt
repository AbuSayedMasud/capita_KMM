package com.leads.capita.bank

interface BankSevice {
    fun getBankListService(): String
    fun getBranchListService(bankId:Int): String
}