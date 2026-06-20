# Docker imagem pronta no dockerHub

## Criando uma imagen e push para dockerhub a partir do projeto.

1. **Deletar imagens antigas**
    ```bash
        docker rmi webflux-cassandra:0.1.0
    ``` 
2. **Build do projeto**
    ```bash
   docker build -t webflux-cassandra:0.1.0 -f docker/Dockerfile .   
   ``` 
3. **Tag do build**
   ```bash
        docker tag c964a0be3604 lucasgalo/webflux-cassandra:0.1.0
   ```
4. **Login dockerhub**
   ```bash
        docker login
   ```
5. **Push da tag**
    ```bash
        docker push lucasgalo/webflux-cassandra:0.1.0
    ```
