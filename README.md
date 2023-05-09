### Pre Requirements
- Install node and npm. Please follow this [guide](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)
- Once npm is installed use this command to install angular cli - `npm install -g @angular/cli@14`
- Install JDK. If you are on mac simply use `brew install openjdk`. This will install latest Java version.

### How to run this Project
- On terminal, from root folder run below command. This command will start Springboot project. 
This project will start springboot project on `localhost:8080` 
```shell
make spring-boot
```

- On another terminal tab, run this command to start angular project. This command will start 
angular project on `localhost:4200`  
``` shell
make serve
```


### TODOs

#### Due to time constraint I couldn't do following which are very much essential

- Create script to build and serve html files via Springboot.
- Add Dockerfile to containerize application.
- Add unit tests on both spring boot and angular side.
- Add following targets in Makefile.
  - Build image
  - Build angular project and copy `Dist` files to `src/main/resources/static`
  - Add single target to start/stop server
- Add default screen either when backend is down or there's no data.

