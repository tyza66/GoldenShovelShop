import React, { useState, useEffect } from 'react';
import request from '../utils/request'
import { Button } from 'antd';
import '../styles/recharge.css';

function Rechargeok() {
    const [user, setUser] = useState({ username: "未登录" })
    const [moeny, setMoeny] = useState("-")
    const [timer,setTimer] = useState("三")
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
        setTimeout(() => {
            setTimer("二")
            setTimeout(() => {
                setTimer("一")
                setTimeout(() => {
                    setTimer("零")
                    window.location.href = "/#/home"
                },1000)
            },1000)
        },1000)
    }, [])
    function home(){
        window.location.href = "/#/home"
    }
    return (
        <>
            <div className="context">
                <div className='header'>
                    <div className='title'>充值</div>
                    <div className='user'>当前用户：{user.username} {(user.username=="未登录")?<Button type="link" onClick={login}>登录</Button>:<Button type="link" onClick={logout}>注销</Button>}</div>
                    <div className='money'>当前余额：{moeny}金币<Button type="link">充值</Button></div>
                </div>
                <div className='main'>
                    <div className='inmain'>
                        <h2 class="title2">充值成功</h2>
                        <h3 class="title2">充值完成，{timer}秒后将自动跳转到主页</h3>
                        <h3 class="title2">如有任何问题请联系客服</h3>
                        <h3 class="title2">https://github.com/tyza66</h3>
                        <button className='b1' onClick={home}> 返回主页 </button>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Rechargeok;