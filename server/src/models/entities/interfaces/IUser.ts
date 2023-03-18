import { Model } from "mongoose";

export interface IUser{

    name: string;
    email: string;
    password: string;
    profileURL: string;
    username: string;
    phoneNumber: string;
}
export interface IUserMethods{
    checkLogin();
    registerUser();
}

export type UserModel = Model<IUser,{}, IUserMethods>
