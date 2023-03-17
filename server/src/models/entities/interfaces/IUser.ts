import { Model } from "mongoose";

export interface IUser{
    name: string;
    email: string;
    password: string;
    avatar: string
}
export interface IUserMethods{
    checkLogin();
    registerUser();
}

export type UserModel = Model<IUser,{}, IUserMethods>