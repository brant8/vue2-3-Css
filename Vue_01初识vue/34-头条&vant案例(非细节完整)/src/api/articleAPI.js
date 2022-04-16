//违章相关API接口，都封装到这个模块中

import request from '@/utils/request.js'

//向外导出一个API函数
export const getArticleListAPI = function(_page, _limit) {
    // console.log("调用了getArticleListAPI函数")
    return request.get('/articles', {
        params: {
            _page: _page,
            _limit: _limit
        }
    })
}