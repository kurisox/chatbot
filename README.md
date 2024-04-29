# chatbot

## Startup AI-Webserver

docker run -d --name AI-Webserver --rm -it -p 8000:8000 -v ./ai-models/path-to-model:/models -e MODEL=/models/model-name.gguf ghcr.io/abetlen/llama-cpp-python:v0.2.61