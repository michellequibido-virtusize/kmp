//
// Created by Michelle D Quibido on 12/21/25.
//

import Foundation
import SwiftUI
import shared
//
struct ComposeUIViewControllerWrapper: UIViewControllerRepresentable {

    func makeUIViewController(context: Context) -> UIViewController {
        // Create your ViewModel or pass any dependencies
        return shared.MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
        // No update logic needed in most cases
    }
}
