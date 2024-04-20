import Foundation
import shared

class ContentViewModel: ObservableObject {
    @Published var accountBalances: [AccountBalance] = []
    
    // Initialize AccountService
    //let loader = IOSMockLoader() // Initialize IOSMockLoader
    let databaseDriverFactory = DatabaseDriverFactory()
    var accountService: AccountService
    var accountBalanceList: [AccountBalance] = []
    
    init() {
        self.accountService = AccountServiceImpl(databaseDriverFactory: databaseDriverFactory)
        self.accountBalanceList = accountService.getBalanceServices()
        self.accountBalances = accountService.getBalanceServices()
        print("ContentViewModel initialized")
        
    }
}
