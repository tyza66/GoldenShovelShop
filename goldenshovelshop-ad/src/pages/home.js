import React, { useState, useEffect } from 'react';
import request from '../utils/request'
import { Button } from 'antd';
import '../styles/home.css';

function Home() {
    const [user, setUser] = useState({ username: "未登录" })
    const [moeny, setMoeny] = useState("-")
    const [count, setCount] = useState(0)
    const [good, setGood] = useState({title:"商品名称",info:"商品介绍",price:"96",store:"100"})
    function getCookie(key) {
        // 将所有cookie键和值存储在一个数组中  
        var cookies = document.cookie.split(';');
        // 遍历数组，查找指定键的cookie  
        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i].trim();
            // 检查cookie是否以键值对的形式存在  
            if (cookie.indexOf('=')) {
                var cookieParts = cookie.split('=');
                // 如果cookie的第一个部分等于指定的键，则返回cookie的值  
                if (cookieParts[0] === key) {
                    return cookieParts[1];
                }
            }
        }
        // 如果没有找到指定的cookie键，则返回null或任何你指定的默认值  
        return null;
    }

    function setCookie(key, value) {
        // 构建新的cookie字符串  
        var cookie = key + '=' + value + ';';
        // 将新cookie字符串添加到现有的cookie字符串中  
        var existingCookies = document.cookie.split(';');
        for (var i = 0; i < existingCookies.length; i++) {
            if (existingCookies[i].trim().indexOf(key) === 0) {
                // 如果找到指定的cookie键，则用新的值替换它  
                existingCookies[i] = cookie;
                break;
            }
        }
        //如果不存在就在后面直接拼接
        if (i == existingCookies.length) {
            existingCookies.push(cookie)
        }
        // 将修改后的cookie字符串重新组合并设置回document.cookie属性  
        document.cookie = existingCookies.join('');
    }

    function removeCookie(key) {
        // 构建一个过期时间为过去的cookie字符串  
        var cookie = key + '=; expires=' + new Date(0).toUTCString() + ';';

        // 将过期的cookie字符串添加到现有的cookie字符串中  
        var existingCookies = document.cookie.split(';');
        for (var i = 0; i < existingCookies.length; i++) {
            if (existingCookies[i].trim().indexOf(key) === 0) {
                // 如果找到指定的cookie键，则用过期时间的cookie字符串替换它  
                existingCookies[i] = cookie;
                break;
            }
        }
        // 将修改后的cookie字符串重新组合并设置回document.cookie属性  
        document.cookie = existingCookies.join('');
    }

    function logout() {
        request.get('/user/logout').then((res) => {
            if (res.data.code == 200) {
                setCookie("satoken", "")
                removeCookie("satoken")
                setTimeout(() => {
                    window.location.reload()
                }, 100);
            }
        }
        )
    }

    useEffect(() => {
        request.get("/user/checkLogin", {
            headers: {
                "satoken": getCookie("satoken")
            }
        }).then((response) => {
            if (response.data.code == 200) {
                setUser(response.data.user)
                request.get("/user/getMoney?username=" + response.data.user.username, {
                    headers: {
                        "satoken": getCookie("satoken")
                    }
                }).then((response) => {
                    setMoeny(response.data)
                }).catch((err) => {
                    console.log(err)
                })
            }
        }).catch((err) => {
            console.log(err)
        })
        request.get("/gifts/num").then((response) => {
            if (response.data.code == 200) {
                setCount(response.data.num)
            }
        }).catch((err) => {
            console.log(err)
        })
        request.get("/goods/thing").then((response) => {
            if (response.data.code == 200) {
                setGood(response.data.good)
            }
        }).catch((err) => {
            console.log(err)
        })
    }, [])

    function login() {
        window.location.href = "#/login"
    }

    function recharge() {
        window.location.href = "#/recharge"
    }

    function getGift(){
        request.get("/gifts/get",{
            headers: {
                "satoken": getCookie("satoken")
            }
        }).then((response) => {
            request.get("/gifts/num").then((response) => {
                if (response.data.code == 200) {
                    setCount(response.data.num)
                }
            }).catch((err) => {
                console.log(err)
            })
        })
    }
    return (
        <>
            <div className="context">
                <div className='header'>
                    <div className='title'>金铲子商店</div>
                    <div className='user'>当前用户：{user.username} {(user.username == "未登录") ? <Button type="link" onClick={login}>登录</Button> : <Button type="link" onClick={logout}>注销</Button>}</div>
                    <div className='money'>当前余额：{moeny}金币<Button type="link" onClick={recharge}>充值</Button></div>
                </div>
                <div>
                    <button class="button gift" onClick={getGift}>
                        <svg class="bell" viewBox="0 0 448 512"><path d="M224 0c-17.7 0-32 14.3-32 32V49.9C119.5 61.4 64 124.2 64 200v33.4c0 45.4-15.5 89.5-43.8 124.9L5.3 377c-5.8 7.2-6.9 17.1-2.9 25.4S14.8 416 24 416H424c9.2 0 17.6-5.3 21.6-13.6s2.9-18.2-2.9-25.4l-14.9-18.6C399.5 322.9 384 278.8 384 233.4V200c0-75.8-55.5-138.6-128-150.1V32c0-17.7-14.3-32-32-32zm0 96h8c57.4 0 104 46.6 104 104v33.4c0 47.9 13.9 94.6 39.7 134.6H72.3C98.1 328 112 281.3 112 233.4V200c0-57.4 46.6-104 104-104h8zm64 352H224 160c0 17 6.7 33.3 18.7 45.3s28.3 18.7 45.3 18.7s33.3-6.7 45.3-18.7s18.7-28.3 18.7-45.3z"></path></svg>
                        领优惠券(剩余{count})
                        <div class="arrow">›</div>
                    </button>
                </div>

                <div class="card">
                    <div class="card-img"><div class="img"></div></div>
                    <div class="card-title">{good.title}</div>
                    <div class="card-subtitle">{good.info}</div>
                    <div class="card-subtitle">库存：{good.store}</div>
                    <hr class="card-divider" />
                    <div class="card-footer">
                        <div class="card-price">{good.price}<span>金币</span></div>
                        <button class="card-btn">
                            <svg viewBox="0 0 512 512" xmlns="http://www.w3.org/2000/svg"><path d="m397.78 316h-205.13a15 15 0 0 1 -14.65-11.67l-34.54-150.48a15 15 0 0 1 14.62-18.36h274.27a15 15 0 0 1 14.65 18.36l-34.6 150.48a15 15 0 0 1 -14.62 11.67zm-193.19-30h181.25l27.67-120.48h-236.6z"></path><path d="m222 450a57.48 57.48 0 1 1 57.48-57.48 57.54 57.54 0 0 1 -57.48 57.48zm0-84.95a27.48 27.48 0 1 0 27.48 27.47 27.5 27.5 0 0 0 -27.48-27.47z"></path><path d="m368.42 450a57.48 57.48 0 1 1 57.48-57.48 57.54 57.54 0 0 1 -57.48 57.48zm0-84.95a27.48 27.48 0 1 0 27.48 27.47 27.5 27.5 0 0 0 -27.48-27.47z"></path><path d="m158.08 165.49a15 15 0 0 1 -14.23-10.26l-25.71-77.23h-47.44a15 15 0 1 1 0-30h58.3a15 15 0 0 1 14.23 10.26l29.13 87.49a15 15 0 0 1 -14.23 19.74z"></path></svg>
                        </button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Home;