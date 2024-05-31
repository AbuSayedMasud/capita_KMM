import SwiftUI
import UIKit

struct DepositRequest: View {
    
    @State private var accountCode = ""
    @State private var amount = ""
    @State private var description = ""
    @State private var bankAccount = ""
    @State private var depositType = ""
    @State private var date = ""
    @State private var transectionRef = ""
    @State private var showImagePicker = false
    @State private var showCameraPicker = false
    @State private var selectedImage: UIImage?
    @State private var isCamera = false
    
    var body: some View {
        ScrollView {
            VStack() {
                inputView(text: $accountCode, title: "Account Code", placeHolder: "Select account code")
                    .padding(.top, 20)
                inputView(text: $bankAccount, title: "Bank Account", placeHolder: "Enter Bank Account")
                    .padding(.top, 10)
                inputView(text: $depositType, title: "Deposit Type", placeHolder: "Select deposit type")
                    .padding(.top, 10)
                inputView(text: $date, title: "Date", placeHolder: "DD/MM/YYYY")
                    .padding(.top, 10)
                inputView(text: $transectionRef, title: "Transection Ref.", placeHolder: "Enter Transection Reference")
                    .padding(.top, 10)
                inputView(text: $amount, title: "Amount", placeHolder: "Enter amount")
                    .padding(.top, 10)
                inputView(text: $description, title: "Description", placeHolder: "Write description")
                    .padding(.top, 10)
                
                // Image with buttons and labels inside the image border
                ZStack {
                    Rectangle()
                        //.strokeBorder(Color.gray, lineWidth: 1)
                        .strokeBorder(style: StrokeStyle(lineWidth: 1, dash: [5]))
                        .frame(width: 307, height: 220)
                        .border(appColor)
                        
                    VStack {
                        if let selectedImage = selectedImage {
                            Image(uiImage: selectedImage)
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .frame(height: 150)
                        } else {
                            Image("download-image-logo")
                                .padding(.top, 10)
                            Text("Let’s upload receipt")
                            Text("Upload your receipt for your deposit request from your gallery or open camera")
                                .font(.subheadline)
                                .fontWeight(.ultraLight)
                                .multilineTextAlignment(.center)
                                .padding(.horizontal, 25)
                                .foregroundColor(.secondary)
                        }
                        
                        HStack(spacing: 30) {
                            VStack {
                                Button(action: {
                                    self.isCamera = false
                                    self.showImagePicker = true
                                }) {
                                    Image("Gallary Button")
                                }
                            }
                            VStack {
                                Button(action: {
                                    self.isCamera = true
                                    self.showImagePicker = true
                                }) {
                                    Image("Camera Button")
                                }
                            }
                        }
                        .padding(.bottom, 10)
                    }
                }
                .padding(.top, 20)
                
                Button(action: {
                    // Submit action
                }, label: {
                    Text("Submit")
                        .fontWeight(.semibold)
                        .foregroundColor(.white)
                        .frame(width: UIScreen.main.bounds.width - 70, height: 48)
                        .background(Color("AccentColor"))
                        .cornerRadius(12)
                })
                .padding()
                
                Spacer()
            }
            .padding(.horizontal, 20)
        }
        .navigationBarTitle("Deposit Request", displayMode: .inline)
        .navigationBarItems(
            leading: Button(action: {
                // Action for the first button
            }) {
                Image("loading_nav_button")
            },
            trailing: HStack {
                Button(action: {
                    // Action for the second button
                    // Show your alert or perform an action
                }) {
                    Image("search_nav_button")
                }
                Button(action: {
                    // Action for another button
                }) {
                    Image("alarm_nav_button")
                }
            }
        )
        .sheet(isPresented: $showImagePicker) {
            ImagePickerforDeposits(sourceType: self.isCamera ? .camera : .photoLibrary, selectedImage: self.$selectedImage)
        }
    }
}

// ImagePickerforDeposits to handle photo library and camera
struct ImagePickerforDeposits: UIViewControllerRepresentable {
    enum SourceType {
        case photoLibrary
        case camera
    }
    
    var sourceType: SourceType
    @Binding var selectedImage: UIImage?
    
    func makeCoordinator() -> Coordinator {
        return Coordinator(self)
    }
    
    func makeUIViewController(context: Context) -> UIImagePickerController {
        let picker = UIImagePickerController()
        picker.delegate = context.coordinator
        picker.sourceType = (sourceType == .camera) ? .camera : .photoLibrary
        return picker
    }
    
    func updateUIViewController(_ uiViewController: UIImagePickerController, context: Context) {}
    
    class Coordinator: NSObject, UINavigationControllerDelegate, UIImagePickerControllerDelegate {
        var parent: ImagePickerforDeposits
        
        init(_ parent: ImagePickerforDeposits) {
            self.parent = parent
        }
        
        func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
            if let image = info[.originalImage] as? UIImage {
                parent.selectedImage = image
            }
            picker.dismiss(animated: true)
        }
    }
}

#Preview {
    DepositRequest()
}
