DS2023 - Trabajo Final Integrador (Grupo 17)

📌 Descripción del Proyecto

Este repositorio contiene el Trabajo Final Integrador (TFI) del curso/materia DS2023 (Grupo 17). El objetivo principal de este proyecto es abordar un problema real a través del ciclo completo de desarrollo, desde la adquisición y preparación de datos hasta el modelado predictivo, la evaluación de métricas y la posterior puesta en producción o presentación de resultados interactivos.
El trabajo refleja buenas prácticas de ingeniería de software y ciencia de datos, enfocándose en la modularidad, la reproducibilidad y la claridad en la presentación de hallazgos para perfiles técnicos y de negocio.

🚀 Características Principales

- Exploración y Limpieza de Datos (EDA): Análisis exhaustivo de variables, detección y tratamiento de datos faltantes/outliers, y visualización de correlaciones clave.
- Ingeniería de Características (Feature Engineering): Transformación, codificación de variables categóricas y normalización/escalado adaptado a los modelos utilizados.
- Modelado Predictivo / ML Pipeline: Entrenamiento y validación cruzada de algoritmos de Machine Learning / Aprendizaje Automático.
- Evaluación e Interpretación: Análisis de rendimiento mediante métricas estándar (Accuracy, F1-Score, ROC-AUC, RMSE, MAE según el caso) y explicación de la importancia de características.
- Aislamiento y Reproducibilidad: Configuración modular y manejo transparente de dependencias.

🛠️ Tecnologías y Herramientas

- Lenguaje Principal: Python 3.9+

- Análisis y Manipulación de Datos: Pandas, NumPy

- Visualización: Matplotlib, Seaborn, Plotly

- Machine Learning & Modelado: Scikit-Learn, XGBoost / LightGBM (ajustar según el pipeline)

- Entorno de Desarrollo: Jupyter Notebooks, VS Code

- Control de Versiones: Git / GitHub

📂 Estructura del Repositorio

DS2023_TFI_G17/
│
├── data/                  # Conjuntos de datos (raw, processed, external)
├── notebooks/             # Notebooks de Jupyter para EDA y experimentación
├── src/                   # Código fuente modular en Python
│   ├── data_processing.py # Scripts de limpieza y transformación
│   ├── features.py        # Generación e ingeniería de variables
│   ├── train.py           # Pipeline de entrenamiento de modelos
│   └── evaluate.py        # Cálculo de métricas y gráficos de evaluación
│
├── models/                # Modelos entrenados y serializados (.pkl, .joblib)
├── reports/               # Gráficos generados, reportes en PDF o presentaciones
│
├── .gitignore             # Archivos y carpetas excluidas del control de versiones
├── requirements.txt       # Lista de dependencias y librerías necesarias
└── README.md              # Documentación principal del proyecto


⚙️ Instalación y Configuración

Para ejecutar este proyecto de forma local, sigue estos pasos:

1. Clonar el repositorio

git clone https://github.com/tu-usuario/DS2023_TFI_G17.git
cd DS2023_TFI_G17

2. Crear y activar un entorno virtual

En Linux/macOS:

python3 -m venv venv
source venv/bin/activate

En Windows (PowerShell):

python -m venv venv
.\venv\Scripts\Activate


3. Instalar las dependencias

pip install --upgrade pip
pip install -r requirements.txt

💻 Uso

Ejecución del Análisis Exploratorio (EDA):
Abre los notebooks ubicados en la carpeta notebooks/ para revisar el flujo paso a paso de análisis de datos y validación de hipótesis.

Entrenamiento del Modelo:
Ejecuta el script principal desde la terminal para entrenar el modelo predictivo: python src/train.py

Evaluación de Resultados:
Genera el reporte de métricas e imágenes asociadas ejecutando: python src/evaluate.py
