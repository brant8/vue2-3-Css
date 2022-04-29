import express from 'express'
import { getAllUser } from '../controller/user_ctrl'

const router = new express.Router()
    //监听get请求
router.get('/user', getAllUser)

export default router