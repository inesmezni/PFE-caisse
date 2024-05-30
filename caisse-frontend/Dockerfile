FROM node:20.12.2 AS lets-dev

WORKDIR /src

COPY . .

RUN npm install -g @angular/cli@17.3.2

EXPOSE 4200

CMD bash -c "npm install --legacy-peer-deps --loglevel verbose && ng serve --host 0.0.0.0 --port 4200 --disable-host-check"