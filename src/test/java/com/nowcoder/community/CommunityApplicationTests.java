package com.nowcoder.community;

import com.nowcoder.community.controler.AlphaController;
import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.dao.AlphaDaoHibernateImpl;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
//引用配置类作为配置文件
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware{
	//applicationContext 继承的顶类是beanFactory
	//当某一个类想要获取bean容器则实现applicationcontextaware接口即可

	private ApplicationContext applicationContext;

	@Test
	public void testSay() {
		AlphaController alphaController =new AlphaController();
		alphaController.say();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Test
	public void showApplicationContext(){
		System.out.println(applicationContext);
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		//当用bean名来调用时，记得添加变量来声明该bean的类型
		//或者通过强制转型来进行调用
		AlphaDao alphaDao1= applicationContext.getBean("alphaDaoHibernateImpl",AlphaDao.class);
		System.out.println(alphaDao.select());;
		System.out.println(alphaDao1.select());
	}
	@Test
	public void beanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}
	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}
	@Autowired
	@Qualifier("alphaDaoHibernateImpl")
	//qualifier注解帮助我们改变优先级，用id来辨别注入的bean
	//spring容器自动将类注入到这个属性中
	private AlphaDao alphaDao;

	@Autowired
	private AlphaService alphaService;

	@Test
	public void testDI(){
		System.out.println(alphaService);
	}
}
