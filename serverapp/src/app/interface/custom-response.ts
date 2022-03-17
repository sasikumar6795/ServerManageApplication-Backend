import { Server } from "./server"

export interface CustomResponse {
    timeStamp: Date,
    statusCode: number,
    status:string,
    reason:string,
    message:string,
    developerMessage?:String,
    data: {
        servers ?: Server[],
        server? : Server
    };

}