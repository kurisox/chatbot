# chatbot

## Requirements

- Docker
- npm and nodejs
- Python 3

## Startup AI-Webserver

### In docker

1. Navigate to in the ai-models directory
2. npm i
1. Download a model with the script in ai-models
2. docker run -d --name AI-Webserver --rm -it -p 8000:8000 -v ./ai-models/path-to-model:/models -e MODEL=/models/model-name.gguf ghcr.io/abetlen/llama-cpp-python:v0.2.61

example: docker run -d --name AI-Webserver --rm -it -p 8000:8000 -v ./ai-models/Llama-2-13B:/models -e MODEL=/models/Llama-2-13B.gguf ghcr.io/abetlen/llama-cpp-python:v0.2.61

### In wsl
1. Connect to wsl
2. Navigate to in the ai-models directory 
3. npm i
4. Download a model with the script in ai-models
5. Setup the enviroment: https://github.com/abetlen/llama-cpp-python/tree/main#openai-compatible-web-server
6. Run python3 -m llama_cpp.server --model models/7B/llama-model.gguf in the console

### Documentation 
 - Llama.cpp: https://github.com/abetlen/llama-cpp-python/ 
 - AI-Webserver: http://localhost:8000/docs 

## Startup CLI-Bot
 - Start with default parameters: gradle -q --console plain run
 - Start with personalized amount of tokens and AI-Persona: gradle -q --console plain run --args='amount of tokens true'
 - The amount of tokens should be bigger than 1024 and smaller than 4096