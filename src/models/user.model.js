import { Schema, model } from 'mongoose';

const userSchema = new Schema(
  {
    name: {
      type: String
    },
    lastname: {
      type: String
    },
    email: {
      type: String
    },
    salary: {
      type: Number
    }
  },
  {
    timestamps: true
  }
);

export default model('User', userSchema);
