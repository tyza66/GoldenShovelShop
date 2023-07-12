import '../styles/login.css';
import React, { useEffect } from 'react';
import { Button, Checkbox, Form, Input, notification } from 'antd';
import request from '../utils/request'

function Login() {
    const [api, contextHolder] = notification.useNotification();
    useEffect(() => {

    })
    const Context = React.createContext({
        name: 'Default',
    });
    
    const openNotification = (placement) => {
        api.info({
            message: `登陆失败`,
            description: <span>giao</span>,
            placement,
        });
    };

    const onFinish = (values) => {
        console.log('Success:', values);
        request.post("/user/login", { "username": values.username, "password": values.password })
            .then(response => {
                console.log(response)
            }).catch(error => {
                console.log(error)
                openNotification('topLeft')
            })
    };
    
    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
        openNotification('topLeft')
    };

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

                        <Form.Item name="remember" valuePropName="checked" wrapperCol={{ offset: 8, span: 16 }}>
                            <Checkbox>记住我</Checkbox>
                        </Form.Item>

                        <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                            <Button type="primary" htmlType="submit">
                                登录
                            </Button>
                        </Form.Item>
                    </Form>
                </div>
            </div>
        </>
    );
}

export default Login;