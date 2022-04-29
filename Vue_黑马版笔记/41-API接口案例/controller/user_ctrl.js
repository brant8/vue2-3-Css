import db from '../db/index.js'

//ES6按需导出
export async function getAllUser(req, res) {
    const result = await db.query('select id,username,nickname from ev_users')
    console.log(result)

    try {
        const [rows] = await db.query('select id,username,nickname from ev_users')
        res.send({
            status: 0,
            message: '获取用户列表成功',
            data: rows
        })
    } catch (e) {
        res.send({
            status: 1,
            message: '获取用户列表失败',
            desc: e.message
        })
    }
}