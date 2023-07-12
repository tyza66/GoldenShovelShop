import React, { useState, useEffect } from 'react';
import request from '../utils/request'
import '../styles/home.css';

function Home() {
    const [user, setUser] = useState({})
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

    useEffect(() => {
        request.get("/user/checkLogin", {
            headers: {
                "satoken": getCookie("satoken")
            }
        }).then((response) => {
            setUser(response.data.user)
        }).catch((err) => {
            console.log(err)
        })
    },[])
    return (
        <>
            <div className="context">
                <div className='header'></div>
            </div>
        </>
    );
}

export default Home;