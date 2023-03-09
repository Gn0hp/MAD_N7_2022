const mongoose = require('mongoose');

const schema = new mongoose.Schema({
    name: String,
    email: String,
    password: String,
    avatar: String,
}, {
    timestamps: true
})

export const User = mongoose.model('User', schema);
