import Foundation
import shared

class AccountBalanceViewModel: ObservableObject {

    @Published var cashBalance: Double = 0.0
    @Published var currentBalance: Double = 0.0
    @Published var equity: Double = 0.0
    @Published var buyingPower: Double = 0.0
    
    // Initialize AccountService
    let loader = IOSMockLoader() // Initialize IOSMockLoader
    let databaseDriverFactory = DatabaseDriverFactory()
    var accountService: AccountService
    
    init() {
        self.accountService = AccountServiceImpl(databaseDriverFactory: databaseDriverFactory)
        fetchAccountBalance() // Fetch account balance when initializing
        
        print("ContentViewModel initialized")
    }
    func fetchAccountBalance() {
        let balanceJSONString = accountService.getBalanceServices()
        if let data = balanceJSONString.data(using: .utf8) {
            do {
                let jsonObject = try JSONSerialization.jsonObject(with: data, options: [])
                if let jsonDict = jsonObject as? [String: Any] {
                    print(jsonDict)
                    if let cashBalance = jsonDict["cashBalance"] as? Double {
                        self.cashBalance = cashBalance
                    }
                    if let equity = jsonDict["equity"] as? Double {
                        self.equity = equity
                    }
                    if let equity = jsonDict["buyingPower"] as? Double {
                        self.buyingPower = equity
                    }
                    
                    if let currentBalance = jsonDict["currentBalance"] as? Double {
                        self.currentBalance = currentBalance
                    }

                }
            } catch {
                print("Error parsing account balance JSON: \(error)")
            }
        }
    }
}
