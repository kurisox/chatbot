# chatbot

## Startup AI-Webserver

1. Download a model with the script in ai-models

2. docker run -d --name AI-Webserver --rm -it -p 8000:8000 -v ./ai-models/path-to-model:/models -e MODEL=/models/model-name.gguf ghcr.io/abetlen/llama-cpp-python:v0.2.61

example: docker run -d --name AI-Webserver --rm -it -p 8000:8000 -v ./ai-models/Llama-2-13B:/models -e MODEL=/models/Llama-2-13B.gguf ghcr.io/abetlen/llama-cpp-python:v0.2.61

Documentation Llama.cpp: https://github.com/ggerganov/llama.cpp
Documentation AI-Webserver: http://localhost:8000/docs 