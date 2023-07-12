import '../styles/register.css';
import React, { useEffect } from 'react';
import { Button, Form, Input, notification } from 'antd';
import request from '../utils/request'

function Register() {
    const [api, contextHolder] = notification.useNotification();
    useEffect(() => {

    })
    
    const openNotification = (info) => {
        if(info == "success") {
            api['success']({
                message: `注册成功`,
                description: <span>正在跳转</span>,
                info,
            });
        }else if(info == "error1") {
            api['error']({
                message: `注册失败`,
                description: <span>请检查你的账号和密码</span>,
                info,
            });
        }else if(info == "error2"){
            api['error']({
                message: `注册失败`,
                description: <span>请检查你的网络情况</span>,
                info,
            });
        }
        else if(info == "error3"){
            api['error']({
                message: `注册失败`,
                description: <span>您的操作过于频繁</span>,
                info,
            });
        }else if(info == "error4"){
            api['error']({
                message: `注册失败`,
                description: <span>两次输入的密码不一致</span>,
                info,
            });
        }
    };

    const onFinish = (values) => {
        console.log('Success:', values);
        if(values.password != values.repassword){
            openNotification('error4')
            return;
        }
        request.post("/user/register", { "username": values.username, "password": values.password })
            .then(response => {
                if(response.data.code == 200){
                    openNotification('success')
                    window.location.href = "../#/login"
                }else if(response.data.code == 202){
                    openNotification('error1')
                }else if(response.data.code == 100){
                    openNotification('error3')
                }
            }).catch(error => {
                //console.log(error)
                openNotification('error2')
            })
    };
    
    const onFinishFailed = (errorInfo) => {
        //console.log('Failed:', errorInfo);
    };

    const login = ()=>{
        window.location.href = "../#/login"
    }

    return (
        <>
            <div className='context'>
                {contextHolder}
                <div className='head'><h2>用户注册</h2></div>
                <div className='main'>
                    <Form
                        name="basic"
                        labelCol={{ span: 8 }}
                        wrapperCol={{ span: 16 }}
                        style={{ maxWidth: 600 }}
                        initialValues={{ remember: true }}
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

                        <Form.Item
                            label="确认密码"
                            name="repassword"
                            rules={[{ required: true, message: '请再次输入密码!' }]}
                        >
                            <Input.Password />
                        </Form.Item>

                        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                            <Button type="primary" htmlType="submit">
                                注册
                            </Button>
                            <Button type="primary" htmlType="button" className='login' onClick={login}>
                                登录
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        </>
    );
}

export default Register;