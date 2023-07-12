import '../styles/login.css';
import React, { useEffect } from 'react';
import { Button, Checkbox, Form, Input, notification } from 'antd';
import request from '../utils/request'

function Login() {
    const [api, contextHolder] = notification.useNotification();
    var localUsername = "", localPassword = ""
    var username = window.sessionStorage.getItem("username");
        var password = window.sessionStorage.getItem("password");
        if (username != null && password != null) {
            localUsername = username
            localPassword = password
        }else{
            window.sessionStorage.setItem("username", "")
            window.sessionStorage.setItem("password", "")
        }
    useEffect(() => {
        
    },[])

    const openNotification = (info) => {
        if (info == "success") {
            api['success']({
                message: `登录成功`,
                description: <span>正在跳转</span>,
                info,
            });
        } else if (info == "error1") {
            api['error']({
                message: `登陆失败`,
                description: <span>请检查你的账号和密码</span>,
                info,
            });
        } else if (info == "error2") {
            api['error']({
                message: `登陆失败`,
                description: <span>请检查你的网络情况</span>,
                info,
            });
        } else if (info == "error3") {
            api['error']({
                message: `登陆失败`,
                description: <span>您的操作过于频繁</span>,
                info,
            });
        }
    };

    const onFinish = (values) => {
        console.log('Success:', values);
        request.post("/user/login", { "username": values.username, "password": values.password })
            .then(response => {
                if (response.data.code == 200) {
                    openNotification('success')
                    if (values.remember == true) {
                        window.sessionStorage.setItem("username", values.username)
                        window.sessionStorage.setItem("password", values.password)
                    }else{
                        window.sessionStorage.setItem("username", "")
                        window.sessionStorage.setItem("password", "")
                    }
                    window.location.href = "../"
                } else if(response.data.code == 202) {
                    openNotification('error1')
                }else if(response.data.code == 100) {
                    openNotification('error3')
                }
            }).catch(error => {
                console.log(error)
                openNotification('error2')
            })
    };

    const onFinishFailed = (errorInfo) => {
        //console.log('Failed:', errorInfo);
    };

    const register = () => {
        window.location.href = "./#/register"
    }

    return (
        <>
            <div className='context'>
                {contextHolder}
                <div className='head'><h2>用户登录</h2></div>
                <div className='main'>
                    <Form
                        name="basic"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                        style={{ maxWidth: 600 }}
                        initialValues={{ remember: false, username: localUsername, password: localPassword }}
                        onFinish={onFinish}
                        onFinishFailed={onFinishFailed}
                        autoComplete="off"
                    >
                        <Form.Item
                            label="用户名"
                            name="username"
                            rules={[{ required: true, message: '请输入用户名!' }]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            label="密码"
                            name="password"
                            rules={[{ required: true, message: '请输入密码!' }]}
                        >
                            <Input.Password />
                        </Form.Item>

                        <Form.Item name="remember" valuePropName="checked" wrapperCol={{ offset: 8, span: 16 }}>
                            <Checkbox>记住我</Checkbox>
                        </Form.Item>

                        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                            <Button type="primary" htmlType="submit">
                                登录
                            </Button>
                            <Button type="primary" htmlType="button" className='register' onClick={register}>
                                注册
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        </>
    );
}

export default Login;