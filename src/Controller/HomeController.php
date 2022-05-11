<?php

namespace App\Controller;
use App\Entity\Tag;
use App\Entity\News;
use App\Services\QrcodeService;
use App\Repository\NewcategorieRepository;

use App\Repository\NewsRepository;
use ContainerJ3jiLHD\PaginatorInterface_82dac15;
use App\Entity\Newcategorie;
use MartinGeorgiev\SocialPost\Message as Message;
use MartinGeorgiev\SocialPost\Publisher;


//use MartinGeorgiev\SocialPost\SocialNetwork;
use MartinGeorgiev\SocialPost\SocialNetwork as SocialNetwork ;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Knp\Component\Pager\PaginatorInterface ;
use Symfony\Component\DependencyInjection\ContainerInterface;
//use Knp\Component\Pager\Pagination ;
use Symfony\Component\DependencyInjection\Container as DependencyInjectionContainer;
use Symfony\Component\DependencyInjection\ParameterBag\ContainerBagInterface ;
use Endroid\QrCode\Builder\BuilderInterface;
use Endroid\QrCodeBundle\Response\QrCodeResponse;


class HomeController extends AbstractController
{
   /**
     * @Route("/", name="home")
     */
    public function index(Request $request,PaginatorInterface $paginator,ContainerInterface $container): Response
    {

$donnees=$this->getDoctrine()->getRepository(News::class)->findBy([],['creation_date'=>'desc']);
$news = $paginator->paginate(
    $donnees, // Rete contenant les données à paginer (ici nos articles)
    $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
   4 // Nombre de résultats par page
   
);
//$post = 'Message for Facebook';
//$message = new Message($post);
//$message->setNetworksToPublishOn([SocialNetwork::FACEBOOK]);

 //$message->setNetworksToPublishOn(FACEBOOK);
//$container->get('social_post')->publish($message);
      //  $message = new message('your test message');
      // $container->get('social_post')->publish($message);
//dd($container);
return $this->render('news/home/index.html.twig', [
    'listNews' => $news,
]);
    }
    
    /**
     * @Route("/tag/{tag}", name="public_site_tag")
     */
    public function byTags(string $tag, Request $request)
    {
        $db = $this->getDoctrine()->getManager();

        $listNews = $db->getRepository(News::class)->findByPage($request->query->getInt('page', 1), 2, $tag);

        return $this->render('news/home/tag.html.twig', [
            'listNews' => $listNews,
        ]);
    }
      /**
     * @Route("/Newcategorie/{Newcategorie}", name="public_site_Newcategorie")
     */
    public function bycategory(string $Newcategorie, Request $request)
    {
        $db = $this->getDoctrine()->getManager();

        $listNews = $db->getRepository(News::class)->findByPage1($request->query->getInt('page', 1), 2, $Newcategorie);

        return $this->render('news/home/category.html.twig', [
            'listNews' => $listNews,
        ]);
    }
}
