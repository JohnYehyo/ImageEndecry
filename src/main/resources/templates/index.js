/*
 * @Author: JohnYehyo
 * @Date: 2022-07-01 00:10:10
 * @LastEditors: JohnYehyo
 * @LastEditTime: 2022-07-01 00:14:06
 * @Description: file content
 * @FilePath: \react-tailwindcss-learnc:\Users\叶佳楠\Desktop\index.js
 */
axios.get('http://localhost:8087/image/decry', {

    imgUrl: "https://tiebapic.baidu.com/forum/pic/item/b9ac2f50f819861826f5d55e0fed2e7389d4e6f6.jpg?tbpicau=2022-07-02-05_8ae7ec464b688c22fbffdb3886446881",
    key: 0.666,
    type: 'rc'

})
.then(function (res) {
    // 请求成功返回
    console.log(res);
})
.catch(function (err) {
    // 请求失败返回
    console.log(err);
})
.then(function () {
    // 不管成功失败都会执行，可以用来关闭加载效果等
});