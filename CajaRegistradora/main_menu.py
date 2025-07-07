from PySide6.QtWidgets import QWidget, QVBoxLayout, QLabel, QPushButton
from PySide6.QtCore import Qt
from PySide6.QtGui import QPixmap, QIcon
import os

class MainMenuPage(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Menú Principal")
        self.setGeometry(100, 100, 500, 400)

        layout = QVBoxLayout()

        # Nombre de la empresa
        self.company_label = QLabel(self.get_company_name())
        self.company_label.setStyleSheet("font-size: 24px; font-weight: bold; color: #333;")
        self.company_label.setAlignment(Qt.AlignCenter)
        layout.addWidget(self.company_label)

        # Botones con estilos personalizados
        buttons_data = [
            ("Realizar venta", "orange"),
            ("Revisión y edición de inventario", "green"),
            ("Consulta de ventas", "red"),
            ("Cálculo de cambio a dólares", "blue"),
            ("Notas y recordatorios", "purple"),
        ]

        for text, color in buttons_data:
            button = QPushButton(text)
            button.setIcon(QIcon("button_image.png"))  # Asigna tu imagen personalizada aquí
            button.setStyleSheet(f"""
                QPushButton {{
                    background-color: {color};
                    border: none;
                    color: white;
                    font-size: 16px;
                    padding: 15px;
                    border-radius: 20px;
                }}
                QPushButton:hover {{
                    background-color: dark{color};
                }}
            """)
            button.clicked.connect(lambda _, b=text: self.handle_button_click(b))
            layout.addWidget(button)

        self.setLayout(layout)

    def get_company_name(self):
        """Leer el nombre de la empresa desde Entreprise_Name.txt"""
        if os.path.exists("Entreprise_Name.txt"):
            with open("Entreprise_Name.txt", "r") as file:
                return file.read().strip()
        return "Nombre de la Empresa"

    def handle_button_click(self, button_name):
        """Manejador para los botones del menú"""
        print(f"Botón presionado: {button_name}")



