package com.epam.preprod.eshop;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.command.CommandController;
import com.epam.preprod.eshop.command.implementation.BuyAllFromBasketCommand;
import com.epam.preprod.eshop.command.implementation.CreateProductCommand;
import com.epam.preprod.eshop.command.implementation.CreateProductWithReflectionCommand;
import com.epam.preprod.eshop.command.implementation.ExitCommand;
import com.epam.preprod.eshop.command.implementation.GetOrderByDateCommand;
import com.epam.preprod.eshop.command.implementation.GetOrderByRangeOfDateCommand;
import com.epam.preprod.eshop.command.implementation.NoCommand;
import com.epam.preprod.eshop.command.implementation.PutProductIntoBasketCommand;
import com.epam.preprod.eshop.command.implementation.ShowAllProductsCommand;
import com.epam.preprod.eshop.command.implementation.ShowBasketCommand;
import com.epam.preprod.eshop.command.implementation.ShowLastFiveProductInBasketCommand;
import com.epam.preprod.eshop.command.implementation.ShowMenuCommand;
import com.epam.preprod.eshop.consoleio.DataInput;
import com.epam.preprod.eshop.consoleio.DataOutput;
import com.epam.preprod.eshop.consoleio.FacadeDataIo;
import com.epam.preprod.eshop.consoleio.implementation.ConsoleInput;
import com.epam.preprod.eshop.consoleio.implementation.ConsoleIo;
import com.epam.preprod.eshop.consoleio.implementation.ConsolePrinter;
import com.epam.preprod.eshop.entity.Basket;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.network.networkcommand.HttpCommand;
import com.epam.preprod.eshop.network.networkcommand.TcpCommand;
import com.epam.preprod.eshop.network.networkcommand.NetworkCommandContainer;
import com.epam.preprod.eshop.network.networkcommand.http.ErrorHttpCommand;
import com.epam.preprod.eshop.network.networkcommand.http.GetProductByIdHttpCommand;
import com.epam.preprod.eshop.network.networkcommand.http.GetProductCountHttpCommand;
import com.epam.preprod.eshop.network.networkcommand.tcp.ErrorTcpCommand;
import com.epam.preprod.eshop.network.networkcommand.tcp.GetProductByIdTcpCommand;
import com.epam.preprod.eshop.network.networkcommand.tcp.GetProductCountTcpCommand;
import com.epam.preprod.eshop.network.parser.RequestParser;
import com.epam.preprod.eshop.network.parser.implementation.HttpParser;
import com.epam.preprod.eshop.network.parser.implementation.TcpParser;
import com.epam.preprod.eshop.network.server.ShopServer;
import com.epam.preprod.eshop.repository.OrderRepository;
import com.epam.preprod.eshop.repository.ProductRepository;
import com.epam.preprod.eshop.repository.implementation.OrderRepositoryImpl;
import com.epam.preprod.eshop.repository.implementation.ProductRepositoryImpl;
import com.epam.preprod.eshop.service.BasketService;
import com.epam.preprod.eshop.service.FacadeService;
import com.epam.preprod.eshop.service.OrderService;
import com.epam.preprod.eshop.service.ProductService;
import com.epam.preprod.eshop.service.implementation.BasketServiceImpl;
import com.epam.preprod.eshop.service.implementation.FacadeServiceImpl;
import com.epam.preprod.eshop.service.implementation.OrderServiceImpl;
import com.epam.preprod.eshop.service.implementation.ProductServiceImpl;
import com.epam.preprod.eshop.tools.inputinteraction.ConsoleInteractionManually;
import com.epam.preprod.eshop.tools.inputinteraction.GenerateInputInteraction;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.tools.productcreator.Creator;
import com.epam.preprod.eshop.tools.productcreator.ProductCreatorsContainer;
import com.epam.preprod.eshop.tools.productcreator.creator.TextBookCreator;
import com.epam.preprod.eshop.tools.productcreator.reflectioncreator.ReflectionProductCreator;
import com.epam.preprod.eshop.tools.storage.StorageTool;
import com.epam.preprod.eshop.tools.storage.filestorage.StateStorageTool;
import com.epam.preprod.eshop.view.View;
import com.epam.preprod.eshop.view.ViewController;
import com.epam.preprod.eshop.view.implementation.AllOrdersView;
import com.epam.preprod.eshop.view.implementation.AllProductView;
import com.epam.preprod.eshop.view.implementation.BasketView;
import com.epam.preprod.eshop.view.implementation.CachedProductView;
import com.epam.preprod.eshop.view.implementation.MenuView;
import com.epam.preprod.eshop.view.implementation.OrderView;
import com.epam.preprod.eshop.view.implementation.ProductView;
import com.epam.preprod.eshop.view.implementation.StringView;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PropertyResourceBundle;
import java.util.Random;
import java.util.ResourceBundle;

import static com.epam.preprod.eshop.message.Messages.HOW_TO_ENTER_PRODUCT;
import static com.epam.preprod.eshop.view.ViewCommands.ALL_ORDERS_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.ALL_PRODUCTS_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.BASKET_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.CACHE_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.MENU_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.ORDER_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.PRODUCT_VIEW;
import static com.epam.preprod.eshop.view.ViewCommands.STRING_VIEW;

public class Initializer {
    private final String PRODUCT_PATH = "src/main/resources/Products.txt";
    private final int HTTP_PORT = 3000;
    private final int TCP_PORT = 3030;

    private CommandController controller;
    private ViewController viewController;

    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private Basket basket;

    private ProductService productService;
    private OrderService orderService;
    private BasketService basketService;
    private FacadeService facadeService;

    private DataInput input;
    private DataOutput output;
    private FacadeDataIo dataIo;
    private Map<Integer, InputInteraction> interaction;
    private List<Class<? extends Product>> productClassList;

    private ProductCreatorsContainer createProductController;
    private Map<Integer, Creator<? extends Product>> creators;
    private ReflectionProductCreator reflectionProductCreator;
    private Map<Class<?>, Method> methodMap;

    private StorageTool<Map<Product, Integer>> storageTool;

    private ResourceBundle resourceBundle;
    private Random random;

    private NetworkCommandContainer<TcpCommand> tcpCommandContainer;
    private NetworkCommandContainer<HttpCommand> httpCommandContainer;
    private RequestParser tcpParser;
    private RequestParser httpParser;
    private ShopServer httpServer;
    private ShopServer tcpServer;


    public Initializer() {
        initIO();
        initInteraction();
        initStorageTool();
        initProductCreator();
        initRepositories();
        initProduct();
        initServices();
        initViewController();
        initCommandController();
        initHttpCommandContainer();
        initTcpCommandContainer();
        initNetworkParsers();
        startUpServers();
    }

    private void initReflectionCreator(InputInteraction chosen) {
        initResourceBundle();
        methodMap = new HashMap<>();
        try {
            methodMap.put(long.class, chosen.getClass().getMethod("readLong", String.class));
            methodMap.put(int.class, chosen.getClass().getMethod("readInteger", String.class));
            methodMap.put(boolean.class, chosen.getClass().getMethod("readBoolean", String.class));
            methodMap.put(String.class, chosen.getClass().getMethod("readString", String.class));
            methodMap.put(LocalDateTime.class, chosen.getClass().getMethod("readLocaleDate", String.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Method Initialization Problem.", e);
        }
        reflectionProductCreator = new ReflectionProductCreator(chosen, resourceBundle, methodMap, dataIo);
    }

    private void initResourceBundle() {
        resourceBundle = PropertyResourceBundle.getBundle("ProductResourceBundle");
    }

    private void initStorageTool() {
        storageTool = new StateStorageTool();
    }

    private void initInteraction() {
        initProductClasses();
        random = new Random();
        interaction = new HashMap<>();
        interaction.put(0, new ConsoleInteractionManually(dataIo));
        interaction.put(1, new GenerateInputInteraction(random, productClassList));
    }

    private void initProductClasses() {
        productClassList = new ArrayList<>();
        productClassList.add(TextBook.class);
    }

    private void initCreators(InputInteraction interaction) {
        creators = new HashMap<>();
        creators.put(0, new TextBookCreator(interaction));
    }


    private void initProductCreator() {
        InputInteraction chosenInteraction = null;
        while (Objects.isNull(chosenInteraction)) {
            chosenInteraction = interaction.get(interaction.get(0).readInteger(HOW_TO_ENTER_PRODUCT));
            initCreators(chosenInteraction);
            initReflectionCreator(chosenInteraction);
            createProductController = new ProductCreatorsContainer(creators, chosenInteraction);
        }
    }

    private void initRepositories() {
        productRepository = new ProductRepositoryImpl();
        orderRepository = new OrderRepositoryImpl();
        basket = new Basket();
    }

    private void initProduct() {
        Map<Product, Integer> products = storageTool.loadState(PRODUCT_PATH);
        products.forEach((product, quantity) -> {
            productRepository.addProduct(product, quantity);
        });
    }

    private void initServices() {
        productService = new ProductServiceImpl(productRepository);
        orderService = new OrderServiceImpl(orderRepository);
        basketService = new BasketServiceImpl(basket);
        facadeService = new FacadeServiceImpl(basketService, orderService, productService);
    }

    private void initIO() {
        input = new ConsoleInput();
        output = new ConsolePrinter();
        dataIo = new ConsoleIo(input, output);
    }

    private void initViewController() {
        Map<String, View> views = new HashMap<>();
        views.put(ALL_PRODUCTS_VIEW, new AllProductView(output));
        views.put(PRODUCT_VIEW, new ProductView(output));
        views.put(BASKET_VIEW, new BasketView(output));
        views.put(CACHE_VIEW, new CachedProductView(output));
        views.put(STRING_VIEW, new StringView(output));
        views.put(ALL_ORDERS_VIEW, new AllOrdersView(output));
        views.put(ORDER_VIEW, new OrderView(output));
        views.put(MENU_VIEW, new MenuView(output));
        viewController = new ViewController(views);
    }

    private CommandController initCommandController() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(1, new ShowAllProductsCommand(facadeService, interaction.get(0), viewController));
        commands.put(2, new ShowBasketCommand(facadeService, interaction.get(0), viewController));
        commands.put(3, new ShowLastFiveProductInBasketCommand(facadeService, interaction.get(0), viewController));
        commands.put(4, new PutProductIntoBasketCommand(facadeService, interaction.get(0), viewController));
        commands.put(5, new BuyAllFromBasketCommand(facadeService, interaction.get(0), viewController));
        commands.put(6, new GetOrderByDateCommand(facadeService, interaction.get(0), viewController));
        commands.put(7, new GetOrderByRangeOfDateCommand(facadeService, interaction.get(0), viewController));
        commands.put(8, new CreateProductCommand(facadeService, interaction.get(0), viewController, createProductController));
        commands.put(9, new CreateProductWithReflectionCommand(facadeService, interaction.get(0), viewController, reflectionProductCreator));
        commands.put(0, new ExitCommand(facadeService, interaction.get(0), viewController, storageTool));
        commands.put(10, new NoCommand(facadeService, interaction.get(0), viewController));
        commands.put(11, new ShowMenuCommand(facadeService, interaction.get(0), viewController));
        controller = new CommandController(commands);
        return controller;
    }

    private void initHttpCommandContainer() {
        Map<String, HttpCommand> commands = new HashMap<>();
        commands.put("item", new GetProductByIdHttpCommand(facadeService));
        commands.put("count", new GetProductCountHttpCommand(facadeService));
        commands.put("Error", new ErrorHttpCommand(facadeService));
        httpCommandContainer = new NetworkCommandContainer<>(commands);
    }

    private void initTcpCommandContainer() {
        Map<String, TcpCommand> commands = new HashMap<>();
        commands.put("item", new GetProductByIdTcpCommand(facadeService));
        commands.put("count", new GetProductCountTcpCommand(facadeService));
        commands.put("Error", new ErrorTcpCommand(facadeService));
        tcpCommandContainer = new NetworkCommandContainer<>(commands);
    }

    private void initNetworkParsers() {
        tcpParser = new TcpParser();
        httpParser = new HttpParser();
    }

    private void startUpServers() {
        tcpServer = new ShopServer(TCP_PORT, tcpParser, tcpCommandContainer);
        httpServer = new ShopServer(HTTP_PORT, httpParser, httpCommandContainer);
        tcpServer.start();
        httpServer.start();
    }

    public CommandController getController() {
        return controller;
    }
}